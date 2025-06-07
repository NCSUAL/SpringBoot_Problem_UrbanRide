1. 스프링 문제 
“UrbanRide”는 서울·부산·대구에 지점을 둔 카셰어링 플랫폼이다.
● 고객은 앱에서 차량 카테고리(Compact / SUV / EV 등)와 대여·반납 시간을 선택해 예약(Reservation) 을 생성한다.
● 확정된 예약은 픽업 시 RentalContract 로 전환된다.
● 주행거리·연장·연료·과태료 등으로 실시간 과금(ChargeItem) 이 누적되며, 반납 시 결제(Payment)가 이루어진다.
● 예약->계약 전환과 차 할당(AssignVehicle)은 재고(Availability) 경쟁 상황이 발생한다.


2. 핵심 ERD
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

Reservation 은 차량이 아직 배정되지 않은 잠정 상태.

RentalContract 에서만 실제 Vehicle 과 매핑(“배차”).

ChargeItem 은 계약 기간 동안 발생하는 동적 행위(주행요금, 연장수수료, 벌금 등)를 모두 다형성-테이블로 기록.

엔티티-관계(카디널리티) 요약
| 관계                                             | 설명                          | JPA 매핑                                                                       
| ---------------------------------------------- | --------------------------- | -----------------------------------------------------------------------------
| **Branch 1 ── \* Vehicle**                     | 한 지점에는 여러 대의 차량이 소속된다.      | `Branch` ←→ `List<Vehicle>` (양방향 선택)                                 
| **Customer 1 ── \* Reservation**               | 한 고객은 여러 예약을 가질 수 있다.       | `Customer` ←→ `List<Reservation>`                                       
| **Reservation 1 ── 1 RentalContract**<br>(선택적) | 예약이 확정되면 정확히 하나의 계약으로 전환된다. | `Reservation` ↔ `RentalContract` (`@OneToOne(optional = false)` 계약 측에서 FK 보유)
| **Vehicle 1 ── 1 RentalContract**              | 계약마다 하나의 차량이 할당된다.          | `RentalContract.vehicle` (`@ManyToOne`)                               
| **RentalContract 1 ── \* ChargeItem**          | 계약 동안 발생한 과금 항목이 여러 개 누적된다. | `RentalContract` ←→ `List<ChargeItem>`                                   
| **RentalContract 1 ── 1 Payment**              | 각 계약은 하나의 결제 레코드를 가진다.      | `Payment` (`@OneToOne(mappedBy = "contract")`)                            

Branch
| 필드     | 타입                  | 제약 / 설명           |
| ------ | ------------------- | ----------------------- |
| `id`   | BIGINT              | **PK**, AUTO\_INCREMENT |
| `name` | VARCHAR(64)         | NOT NULL, UNIQUE        |
| `city` | VARCHAR(32) 또는 ENUM | NOT NULL               |

Vehicle
| 필드              | 타입                           | 제약 / 설명               |
| --------------- | ---------------------------- | ---------------------------- |
| `vin`           | CHAR(17)                     | **PK** (차대번호)             |
| `vehicle_class` | ENUM(`COMPACT`,`SUV`,`EV` …) | NOT NULL                     |
| `branch_id`     | BIGINT                       | **FK → Branch.id**, NOT NULL |
| `version`       | BIGINT                       | JPA **@Version** 낙관적 락    |


Customer
| 필드    | 타입          | 제약 / 설명                 |
| ------- | ------------ | ----------------------- |
| `id`    | BIGINT       | **PK**, AUTO\_INCREMENT |
| `name`  | VARCHAR(64)  | NOT NULL                |
| `email` | VARCHAR(128) | NOT NULL, UNIQUE        |
| `phone` | VARCHAR(20)  | NULLABLE                |


Reservation
| 필드              | 타입                                     | 제약 / 설명                        |
| ----------------- | --------------------------------------- | ------------------------------ |
| `id`              | BIGINT                                  | **PK**, AUTO\_INCREMENT        |
| `customer_id`     | BIGINT                                  | **FK → Customer.id**, NOT NULL |
| `branch_id`       | BIGINT                                  | **FK → Branch.id**, NOT NULL   |
| `requested_class` | ENUM                                    | NOT NULL                       |
| `start_at`        | DATETIME                                | NOT NULL                       |
| `end_at`          | DATETIME                                | NOT NULL                       |
| `status`          | ENUM(`PENDING`,`CONFIRMED`,`CANCELLED`) | NOT NULL                       |
| `version`         | BIGINT                                  | **@Version**                   |
| **인덱스**        | `(branch_id, start_at, end_at)`         | 가용 차량 조회용                 |


RentalContract
| 필드                     | 타입           | 제약 / 설명                                |
| ------------------------ | ------------- | ----------------------------------------- |
| `id`                     | BIGINT        | **PK**, AUTO\_INCREMENT                   |
| `reservation_id`         | BIGINT        | **FK → Reservation.id**, UNIQUE, NOT NULL |
| `vehicle_vin`            | CHAR(17)      | **FK → Vehicle.vin**, NOT NULL            |
| `picked_up_at`           | DATETIME      | NOT NULL                                  |
| `returned_at`            | DATETIME      | NULLABLE                                  |
| **`base_fee_amount`**    | DECIMAL(18,2) | NOT NULL                                  |
| **`base_fee_currency`**  | CHAR(3)       | NOT NULL                                  |
| **`total_fee_amount`**   | DECIMAL(18,2) | NOT NULL                                  |
| **`total_fee_currency`** | CHAR(3)       | NOT NULL                                  |
| `version`                | BIGINT        | **@Version**                              |

ChargeItem (단일 테이블 상속)
| 공통 필드      | 타입          | 제약 / 설명                           |
| ------------- | ------------- | ------------------------------------ |
| `id`          | BIGINT        | **PK**, AUTO\_INCREMENT              |
| `contract_id` | BIGINT        | **FK → RentalContract.id**, NOT NULL |
| `charge_type` | VARCHAR(31)   | **Discriminator**, NOT NULL          |
| `amount`      | DECIMAL(18,2) | NOT NULL                             |
| `currency`    | CHAR(3)       | NOT NULL                             |
| `occurred_at` | DATETIME      | NOT NULL                             |


| 서브타입                   | 추가 필드        | 타입 / 설명   |
| ------------------------- | ---------------- | ------------ |
| **DrivingDistanceCharge** | `distance_km`    | DOUBLE       |
| **FuelCharge**            | `liters_missing` | DOUBLE       |
| **PenaltyCharge**         | `reason`         | VARCHAR(128) |


Payment
| 필드                      | 타입                                            | 제약 / 설명                                      
| ----------------------- | --------------------------------------------- | -------------------------------------------- |
| `id`                    | BIGINT                                        | **PK**, AUTO\_INCREMENT                      |
| `contract_id`           | BIGINT                                        | **FK → RentalContract.id**, UNIQUE, NOT NULL |
| `status`                | ENUM(`INIT`,`AUTHORIZED`,`CAPTURED`,`FAILED`) | NOT NULL                                     |
| **`captured_amount`**   | DECIMAL(18,2)                                 | NULLABLE                                     |
| **`captured_currency`** | CHAR(3)                                       | NULLABLE                                     |


| 베이스          | 전략         | 테이블                                        
| ------------ | -------------- | --------------------------------------------- | --------------------------------------------------------------- 
| `ChargeItem` | `SINGLE_TABLE` | 하나의 `charge_items` 테이블에 `charge_type` 컬럼으로 구분 |


REST API
| 메서드      | 엔드포인트                                 | 설명                                                                     
| -------- | ------------------------------------- | ---------------------------------------------------------------------- 
| `POST`   | `/api/reservations`                   | **예약 생성** – 요청: `{customerId, branchId, vehicleClass, startAt, endAt}` 
| `POST`   | `/api/reservations/{id}/confirm`      | 예약 확정 & 차량 자동 배정 (Service 내부에서 재고 확인)                                  
| `DELETE` | `/api/reservations/{id}`              | 취소 (타이밍 따라 PenaltyCharge 자동 생성)                                        
| `POST`   | `/api/contracts/{id}/charges/driving` | 주행거리 요금 추가 `{distanceKm}`                                              
| `POST`   | `/api/contracts/{id}/charges/fuel`    | 연료 요금 추가 `{litersMissing}`                                             
| `POST`   | `/api/contracts/{id}/return`          | 반납 → `returnedAt` 기록, 총합 요금 계산                                        
| `POST`   | `/api/contracts/{id}/payment`         | 결제 시도 – 카드 토큰 전달, status 업데이트                                          
| `GET`    | `/api/customers/{id}/contracts`       | 고객의 계약 목록 + `totalFee`, `paymentStatus` Projection                     
