# 📝구현할 기능 목록

## 플로우 차트

<img width="735" alt="스크린샷 2023-11-11 오후 4 27 04" src="https://github.com/GDSC-Hongik/2023-2-OC-Java-Study/assets/66353672/7a3ca08f-b56b-40f8-9765-0dc012b708da">

## 식당 예상 방문 날짜 입력받기

- [x] 식당 예상 방문 날짜(day)만 입력 받는다.
   - [x]  1이상 31이하의 숫자를 입력받는다.
   - [x]  예외처리: 이 외의 숫자 또는 문자를 입력하는 경우

## 달력 초기화

- [x] 12월 이벤트 달력을 초기화 한다
   - [x]  주말/평일을 구분한다.
   - [x]  별(특별한 날)을 구분한다.
   
## 메뉴판 초기화

- [x]  메뉴판을 등록한다.
   - [x]  메뉴이름,가격, 카테코리를 저장한다.

```java
// 저장할 메뉴
<애피타이저>
양송이수프(6,000), 타파스(5,500), 시저샐러드(8,000)

<메인>
티본스테이크(55,000), 바비큐립(54,000), 해산물파스타(35,000), 크리스마스파스타(25,000)

<디저트>
초코케이크(15,000), 아이스크림(5,000)

<음료>
제로콜라(3,000), 레드와인(60,000), 샴페인(25,000)
```

## 주문할 메뉴와 개수 입력받기

- [x]  메뉴판에 있는 메뉴와 개수를 입력받는다. ex) 해산물파스타-2,레드와인-1,초코케이크-1
   - [x]  예외처리: 메뉴판에 없는 메뉴를 입력하는 경우
   - [x]  예외처리: 메뉴 갯수가 1이상의 숫자가 아닌 경우
   - [x]  예외처리: 메뉴 형식이 예시와 다른 경우
   - [x]  예외처리: 음료만 주문시
   - [x]  예외처리: 메뉴의 총 갯수가 20개 초과일경우
   - [x]  예외처리: 중복 메뉴를 입력한 경우. ex) 시저샐러드-1,시저샐러드-1

## 주문 메뉴 출력하기

- [x]  주문한 메뉴와 갯수를 출력한다.

ex)

```java
<주문 메뉴>

타파스 1개

        제로콜라 1개
```

## 할인 전 총 주문 금액 구하기

- [x]  각 메뉴와 갯수를 이용해 총 주문 금액을 구한다.

---

## 디데이 할인 구하기

- [x]  할인 금액: 1000원 + (입력받은 날짜 - 1) * 100 을 구한다.
   - [x]  예외 상황: 입력받은 날짜가 1~25이 아닌 경우
   - [x]  예외 상황: 총주문 금액이 10,000 이상이 아닌 경우

## 평일 할인 구하기

- [x]  디저트 메뉴를 메뉴 1개당 2,023원 할인한다.
   - [x]  예외 상황: 총주문 금액이 10,000 이상이 아닌 경우

## 주말 할인 구하기

- [x]  메인 메뉴를 메뉴 1개당 2,023원 할인한다.
   - [x]  예외 상황: 총주문 금액이 10,000 이상이 아닌 경우

## 특별할인

- [x]  입렫받은 날짜에 별이 달려있으면 총 주문 금액에서 1000원 할인한다.
   - [x]  예외 상황: 총주문 금액이 10,000 이상이 아닌 경우

## 증정 이벤트

- [x]  할인 전 총 주문 금액이 12만원 이상일때, 샴페인 1개 증정한다.
- [x]  증정 메뉴가 있을 경우, 증정 메뉴를 출력한다. 없을 경우 “없음”을 출력한다.

```java
// 증정 메뉴가 있을 시
<증정 메뉴>
삼페인 1개
// 증정 메뉴가 없을 시
<증정 메뉴>
없음
```

## 혜택 내역 출력하기

- [x]  고객에게 적용된 이벤트 내역만 출력한다. 없을 경우 “없음”을 출력한다.

```java
// 혜택 내역 있을 경우
<혜택 내역>
크리스마스 디데이 할인: -1,200원
        평일 할인: -4,046원
        특별 할인: -1,000원
        증정 이벤트: -25,000원
// 혜택 내역 없을 경우
<혜택 내역>
없음
```

---

## 총혜택 금액 계산하기

- [x]  (크리스마스 디데이 할인 + 평일 할인 + 특별할인 + 증정 이벤트) 값을 계산한다.
- [x]  계산된 금액을 출력한다.

## 할인 후 예상 결제 금액 출력하기

- [x]  할인 전 총주문 금액 - (크리스마스 디데이 할인 + 평일 할인 + 특별 할인) 값을 계산한다.
- [x]  계산된 금액을 출력한다.

## 12월 이벤트 배지 출력하기

- [x]  총혜택 금액이 5천원 미만인 경우: “없음” 출력한다.
- [x]  총혜택 금액이 5천원 이상 1만원 미만인 경우: “별”을 출력한다.
- [x]  총혜택 금액이 1만원 이상 2만원 미만인 경우: “트리”를 출력한다.
- [x]  총혜택 금액이 2만원 이상인 경우: “산타”를 출력한다.