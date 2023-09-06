# shop
## 💻Tech Stack
### Back-end
|   Java   |   Spring Boot   |   Spring Data JPA   |   Spring Security   |   H2   |
| :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: |
| <div style="display: flex; align-items: flex-start;"><img src="https://techstack-generator.vercel.app/java-icon.svg" alt="icon" width="65" height="65" /></div> |  <img alt="spring-boot logo" src="https://t1.daumcdn.net/cfile/tistory/27034D4F58E660F616" width="65" height="65" > |<img alt="spring logo" src="https://www.vectorlogo.zone/logos/springio/springio-icon.svg" height="50" width="50" > |  <img alt="spring-security logo" width="60px" src="https://camo.githubusercontent.com/923e99a57f8a456fdade5f65b35ada254be277612ddc991afb702d8dfd880d4f/68747470733a2f2f63646e2e73696d706c6569636f6e732e6f72672f737072696e677365637572697479" width="85" height=auto > | <div style="display: flex; align-items: flex-start;"><img src="https://github.com/lsh4711/shop/assets/120231876/9e5d84ca-e232-4973-8c79-e3abe3793ffd" alt="icon" width="65" height="65" /></div> |


</br>

### Tools
| Github | Spring Boot Devtools | 
| :--------: | :--------: |
| <img alt="github logo" src="https://techstack-generator.vercel.app/github-icon.svg" width="65" height="65"> | <img alt="Spring Boot logo" src="https://t1.daumcdn.net/cfile/tistory/27034D4F58E660F616" height="65" width="65"> |


</br>

## 📃Docs

[**DB 스키마**]
![image](https://github.com/lsh4711/shop/assets/120231876/02d7d89a-f882-4392-a204-fa178b9ef1d9)

## 메모
```
// Member
[Post] /api/members/register (회원 등록): 아이디 중복, 형식 검사, 비밀번호 형식 검사
[Get, Post] /api/members/login (로그인): Authorization 헤더로 jwt 토큰 반환, 만료 시간 환경 변수로 설정함
[Delete] /api/members (회원 탈퇴)

[Post] /api/members/cart/items/add (장바구니에 상품 추가): 여러번 추가 시 개수만 증가
[Patch] /api/members/cart/items/{cartItemId}/edit (장바구니 상품 수량 변경)
[Get] /api/members/cart/items (장바구니 목록 조회): 각 상품 정보와 함께 각 상품 가격, 배송비, 총 금액 확인 가능
[Delete] /api/members/cart/items/{cartItemId} (장바구니에서 상품 삭제)
[Delete] /api/members/cart/items (장바구니 비우기)
[Patch] /api/members/cart/items/discount (쿠폰 적용 & 금액 확인): 할인 전 각 상품 금액, 배송비, 총금액 + 할인 후 각 상품 금액, 배송비, 총금액 확인 가능

// Mart
[Post] /api/marts/register (마트 등록): 마트 이름 중복 검사, 마트 최대 보유 수량 5개 넘지않도록 제한 ## Seller 권환 되돌리는거 잊지 않기
[Get] /api/marts/public?page={page}&size={size} (마트 목록 조회)
[Get] /api/marts/private (본인 소유의 마트 목록 조회): 최대 5개, 정산금 확인 가능

// Item
[Post] /api/items/register (서버에 등록된 제품 정보로 본인 마트에 상품 등록): 제품 중복 검사, 등록 시 Item_History 테이블에 가격 기록, 트랜잭션
[Patch] /api/items/{itemId}/edit (본인이 등록한 상품 가격 수정): 수정 시 Item_History 테이블에 가격 기록, 트랜잭션
[Get] /api/items?martId={martId} (해당 마트의 상품 목록 조회)
[Get] /api/items/{itemId} (해당 상품 정보 조회)
[Delete] /api/items/{itemId} (해당 상품 삭제) ## 우선은 삭제하지 못하도록 함

[Get] /api/items/{itemId}/price/histories/search?&date=yyyy.MM.ddTHH:mm:ss (해당 시점의 상품 가격 확인)
[Get] /api/items/{itemId}/price/histories?days={days} (최근 N일간의 상품 가격 차트 조회)

// Brand
[Post] /api/brands/register (상품 등록전 제품 제조사 등록): 이름 중복 검사
[Get] /api/brands (제조사 목록 조회)

// Category
[Post] /api/categories/register (상품 등록 전 제품 분류명 등록): 이름 중복 검사
[Get] /api/categories (카테고리 목록 조회)

// Product
[Post] /api/products/register (상품 등록 전 제품 등록): 바코드 중복 검사
[Get] /api/products (제품 목록 조회)

// Coupon
[Get] /api/coupons (본인의 쿠폰 목록 조회)

// Order
[Post] /api/orders/pay (장바구니 결제): 결제 후 사용된 쿠폰은 삭제, 해당 상품을 등록한 마트에 정산금 추가, 장바구니는 비워짐
[Get] /api/orders (본인의 주문 내역 조회)
[Get] /api/orders/{orderId} (주문 내역과 상품 상세 보기)
```
