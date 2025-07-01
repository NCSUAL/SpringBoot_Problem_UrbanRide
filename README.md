# UrbanRide 카셰어링 시스템 개요

## 도메인 설명

UrbanRide는 서울, 부산, 대구에 지점을 둔 카셰어링 플랫폼입니다.

* 고객은 앱에서 차량 카테고리(Compact, SUV, EV 등)와 대여/반납 시간을 선택하여 예약(Reservation)을 생성합니다.
* 확정된 예약은 픽업 시 RentalContract로 전환됩니다.
* 계약 기간 동안 주행거리, 연장, 연료, 과태료 등의 과금 항목(ChargeItem)이 발생하고, 반납 시 결제(Payment)로 이어집니다.
* 예약 확정 및 차량 할당은 재고(Availability) 경쟁이 발생하는 민감한 작업입니다.

---

## 핵심 ERD 요약

```
Branch ──< Vehicle (PK: vin)
   │1
   │        1                1
Customer >── Reservation ────┤
              │ 1          1 │
              │             │
              ▼             ▼
       RentalContract ──< ChargeItem
              │1
              │
              ▼
           Payment
```

* **Reservation**: 차량이 아직 배정되지 않은 잠정 상태
* **RentalContract**: 확정된 계약이며 차량과 매핑됨
* **ChargeItem**: 계약 중 발생하는 요금 항목 (단일 테이블 상속 방식)

---

## 엔티티 관계 설명 (JPA 매핑)

| 관계                                | 설명              | JPA 매핑                                     |
| --------------------------------- | --------------- | ------------------------------------------ |
| Branch 1 ── \* Vehicle            | 한 지점에 여러 차량 소속  | `Branch` ↔ `List<Vehicle>`                 |
| Customer 1 ── \* Reservation      | 고객은 여러 예약 가능    | `Customer` ↔ `List<Reservation>`           |
| Reservation 1 ── 1 RentalContract | 예약 확정 시 계약으로 전환 | `@OneToOne(optional = false)` - 계약에서 FK 보유 |
| Vehicle 1 ── 1 RentalContract     | 계약마다 차량 하나 배정   | `@ManyToOne`                               |
| RentalContract 1 ── \* ChargeItem | 계약 중 과금 항목 발생   | `List<ChargeItem>`                         |
| RentalContract 1 ── 1 Payment     | 계약당 결제 1개       | `@OneToOne(mappedBy = "contract")`         |

---

### Branch

| 필드   | 타입                  | 설명                  |
| ---- | ------------------- | ------------------- |
| id   | BIGINT              | PK, AUTO\_INCREMENT |
| name | VARCHAR(64)         | NOT NULL, UNIQUE    |
| city | VARCHAR(32) 또는 ENUM | NOT NULL            |

### Vehicle

| 필드             | 타입       | 설명             |
| -------------- | -------- | -------------- |
| vin            | CHAR(17) | PK             |
| vehicle\_class | ENUM     | NOT NULL       |
| branch\_id     | BIGINT   | FK → Branch.id |
| version        | BIGINT   | @Version 낙관적 락 |

### Customer

| 필드    | 타입           | 설명                  |
| ----- | ------------ | ------------------- |
| id    | BIGINT       | PK, AUTO\_INCREMENT |
| name  | VARCHAR(64)  | NOT NULL            |
| email | VARCHAR(128) | NOT NULL, UNIQUE    |
| phone | VARCHAR(20)  | NULLABLE            |

### Reservation

| 필드                  | 타입                                  | 설명                  |
| ------------------- | ----------------------------------- | ------------------- |
| id                  | BIGINT                              | PK, AUTO\_INCREMENT |
| customer\_id        | BIGINT                              | FK → Customer.id    |
| branch\_id          | BIGINT                              | FK → Branch.id      |
| requested\_class    | ENUM                                | NOT NULL            |
| start\_at / end\_at | DATETIME                            | NOT NULL            |
| status              | ENUM(PENDING, CONFIRMED, CANCELLED) | NOT NULL            |
| version             | BIGINT                              | @Version            |
| 인덱스                 | (branch\_id, start\_at, end\_at)    | 가용 차량 조회용           |

### RentalContract

| 필드                            | 타입                | 설명                          |
| ----------------------------- | ----------------- | --------------------------- |
| id                            | BIGINT            | PK                          |
| reservation\_id               | BIGINT            | FK → Reservation.id, UNIQUE |
| vehicle\_vin                  | CHAR(17)          | FK → Vehicle.vin            |
| picked\_up\_at / returned\_at | DATETIME          | 픽업은 NOT NULL, 반납은 NULLABLE  |
| base\_fee\_amount / currency  | DECIMAL / CHAR(3) | NOT NULL                    |
| total\_fee\_amount / currency | DECIMAL / CHAR(3) | NOT NULL                    |
| version                       | BIGINT            | @Version                    |

### ChargeItem (SINGLE\_TABLE)

| 필드                | 타입                | 설명                     |
| ----------------- | ----------------- | ---------------------- |
| id                | BIGINT            | PK                     |
| contract\_id      | BIGINT            | FK → RentalContract.id |
| charge\_type      | VARCHAR(31)       | Discriminator          |
| amount / currency | DECIMAL / CHAR(3) | NOT NULL               |
| occurred\_at      | DATETIME          | NOT NULL               |

#### ChargeItem 서브타입

* DrivingDistanceCharge → distance\_km (DOUBLE)
* FuelCharge → liters\_missing (DOUBLE)
* PenaltyCharge → reason (VARCHAR(128))

### Payment

| 필드                          | 타입                                       | 설명                             |
| --------------------------- | ---------------------------------------- | ------------------------------ |
| id                          | BIGINT                                   | PK                             |
| contract\_id                | BIGINT                                   | FK → RentalContract.id, UNIQUE |
| status                      | ENUM(INIT, AUTHORIZED, CAPTURED, FAILED) | NOT NULL                       |
| captured\_amount / currency | DECIMAL / CHAR(3)                        | NULLABLE                       |


## 개발 현왕

| 관계    | 설명                                  | 상황                                                         |
| --------------------------- | ---------------------------------------- | ------------------------------ |
| Branch 1 ── \* Vehicle            | 한 지점에 여러 차량 소속  | <a href ="#">개발중</a>              |
| Customer 1 ── \* Reservation      | 고객은 여러 예약 가능    | 개발중        |
| Reservation 1 ── 1 RentalContract | 예약 확정 시 계약으로 전환 | 시작전 |
| Vehicle 1 ── 1 RentalContract     | 계약마다 차량 하나 배정   | 시작전                              |
| RentalContract 1 ── \* ChargeItem | 계약 중 과금 항목 발생   | 시작전                     |
| RentalContract 1 ── 1 Payment     | 계약당 결제 1개       | 시작전       |



## 테이블 전략

* ChargeItem은 SINGLE\_TABLE 전략 사용
* charge\_type 컬럼으로 타입 구분

