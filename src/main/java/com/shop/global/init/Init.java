package com.shop.global.init;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import com.shop.domain.brand.entity.Brand;
import com.shop.domain.brand.service.BrandService;
import com.shop.domain.category.entity.Category;
import com.shop.domain.category.service.CategoryService;
import com.shop.domain.coupon.entity.Coupon;
import com.shop.domain.coupon.entity.Coupon.Info;
import com.shop.domain.coupon.service.CouponService;
import com.shop.domain.item.entity.Item;
import com.shop.domain.item.entity.PriceHistory;
import com.shop.domain.item.repository.PriceHistoryRepository;
import com.shop.domain.item.service.ItemService;
import com.shop.domain.mart.entity.Mart;
import com.shop.domain.mart.service.MartService;
import com.shop.domain.member.dto.CartItemResponse;
import com.shop.domain.member.dto.CartResponse;
import com.shop.domain.member.entity.CartItem;
import com.shop.domain.member.entity.Member;
import com.shop.domain.member.entity.Member.Role;
import com.shop.domain.member.mapper.MemberMapper;
import com.shop.domain.member.service.MemberService;
import com.shop.domain.order.entity.Order;
import com.shop.domain.order.entity.Order.Payment;
import com.shop.domain.order.mapper.OrderMapper;
import com.shop.domain.order.service.OrderService;
import com.shop.domain.product.entity.Product;
import com.shop.domain.product.entity.ProductCategory;
import com.shop.domain.product.service.ProductCategoryService;
import com.shop.domain.product.service.ProductService;
import com.shop.global.auth.filter.JwtAuthenticationToken;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class Init {
    private final MemberService memberService;
    private final MemberMapper memberMapper;

    private final MartService martService;

    private final BrandService brandService;

    private final CategoryService categoryService;

    private final ProductService productService;

    private final ProductCategoryService productcategoryService;

    private final ItemService itemService;

    private final PriceHistoryRepository priceHistoryRepository;

    private final CouponService couponService;

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    // @PostConstruct
    private void init() {
        setContext();

        initMembers();
        initBrands();
        initCategories();
        initProducts();
        inititems();
        // initCartItems();
        initCoupons();

        initOrders(20);
    }

    private void setContext() {
        Authentication authentication = new JwtAuthenticationToken(1, null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private void initMembers() {
        for (int i = 1; i <= 10; i++) {
            Role role = i <= 5 ? Role.SELLER : Role.USER;
            String encryptedPassword = memberService.encryptPassword("member" + i);
            Member member = new Member();
            member.setUsername("member" + i);
            member.setPassword(encryptedPassword);
            member.setAddress("무슨동 무슨길 " + i);
            member.setRole(role);
            memberService.createMember(member);
            initMarts(member);
        }
    }

    private void initMarts(Member member) {
        if (member.getRole() != Role.SELLER) {
            return;
        }

        String[] martNames = {"동네 마트", "이마트", "농림 마트", "농협 마트", "편의점"};

        for (int i = 1; i <= 4; i++) {
            Mart mart = new Mart();
            mart.setName(martNames[i - 1] + member.getMemberId());
            mart.setAddress("무슨동 무슨길 " + member.getMemberId() + i);
            mart.setMember(member);

            martService.createMart(mart);
        }
    }

    private void initBrands() {
        String[] brandNames = {"농심", "롯데", "동원", "오뚜기", "풀무원", "하림", "동서", "사조", "칠성", "샘표"};
        for (int i = 1; i <= 10; i++) {
            Brand brand = new Brand();
            brand.setName(brandNames[i - 1]);
            brand.setAddress("무슨동 무슨길 " + i);

            brandService.createBrand(brand);
        }
    }

    private void initCategories() {
        String[] categoryNames = {"음료", "채소", "해산물", "육류", "유제품", "과자", "빵류", "빙과류", "면류", "즉석 식품"};
        for (int i = 1; i <= 10; i++) {
            Category category = new Category();
            category.setName(categoryNames[i - 1]);
            categoryService.createCategory(category);
        }
    }

    private void initProducts() {
        String[] productNames = {
            "아침 사과 주스 200ml",
            "찌개용 한입 두부 200g",
            "마늘 훈제 닭고기 300g",
            "멸치 육수 1L",
            "민트 초코 우유 500ml",
            "초코에몽 200ml",
            "동원 참치 캔 200g",
            "짜파게티 5개입",
            "허쉬 다크 초콜릿 100g",
            "신선한 달걀 30개입"
        };
        for (long i = 1; i <= 10; i++) {
            Brand brand = new Brand();
            brand.setBrandId(i);

            Product product = new Product();
            product.setName(productNames[(int)i - 1]);
            product.setBarcode(Long.toString(i).repeat(8));
            product.setBrand(brand);

            productService.createProduct(product);

            for (long j = i; j < i + 3; j++) {
                Category category = new Category();
                category.setCategoryId(j % 10 + 1);

                ProductCategory productCategory = new ProductCategory(null, product, category);

                productcategoryService.createProductCategory(productCategory);
            }
        }
    }

    private void inititems() {
        for (long i = 1; i <= 9; i++) {
            Mart mart = new Mart();
            mart.setMartId(1L);

            Product product = new Product();
            product.setProductId(i);

            Item item = new Item();
            item.setMart(mart);
            item.setProduct(product);
            item.setPrice(i * 450);

            itemService.createItem(item);
            generateChartDatas(item);
        }
    }

    private void generateChartDatas(Item item) {
        Random random = new Random();
        for (int i = 89; i >= 1; i--) {
            PriceHistory priceHistory = new PriceHistory();
            priceHistory.setItem(item);
            priceHistory.setPrice(random.nextLong(300) * 10 + 1000);
            priceHistory.setModifiedAt(LocalDateTime.now().minusDays(i).withNano(0));
            priceHistoryRepository.save(priceHistory);
        }
        PriceHistory priceHistory = new PriceHistory();
        priceHistory.setItem(item);
        priceHistory.setPrice(item.getPrice());
        priceHistoryRepository.save(priceHistory);
    }

    private void initCartItems() {
        Random random = new Random();

        for (long i = 1; i <= 9; i++) {
            Member member = new Member();
            member.setMemberId(1L);

            Item item = new Item();
            item.setItemId(i);

            CartItem cartItem = new CartItem();
            cartItem.setAmount(random.nextLong(5) + 1);
            cartItem.setMember(member);
            cartItem.setItem(item);

            memberService.addCartItem(cartItem);
        }
    }

    private void initCoupons() {
        for (long i = 1; i <= 3; i++) {
            Member member = new Member();
            member.setMemberId(1L);
            Coupon coupon = new Coupon();
            coupon.setName("특정 상품 고정 할인 쿠폰" + i);
            coupon.setInfo(Info.FIX_EACH);
            coupon.setDiscountValue((int)i * 500);
            coupon.setMember(member);
            coupon.setTargetProductId(i);

            couponService.createCoupon(coupon);
        }
        for (long i = 1; i <= 3; i++) {
            Member member = new Member();
            member.setMemberId(1L);
            Coupon coupon = new Coupon();
            coupon.setName("특정 상품 비율 할인 쿠폰" + i);
            coupon.setInfo(Info.RATE_EACH);
            coupon.setDiscountValue((int)i * 5);
            coupon.setMember(member);
            coupon.setTargetProductId(i);

            couponService.createCoupon(coupon);
        }
        for (long i = 1; i <= 3; i++) {
            Member member = new Member();
            member.setMemberId(1L);
            Coupon coupon = new Coupon();
            coupon.setName("전체 상품 비율 할인 쿠폰" + i);
            coupon.setInfo(Info.RATE_ALL);
            coupon.setDiscountValue((int)i * 5);
            coupon.setMember(member);

            couponService.createCoupon(coupon);
        }
        for (long i = 1; i <= 3; i++) {
            Member member = new Member();
            member.setMemberId(1L);
            Coupon coupon = new Coupon();
            coupon.setName("전체 상품 고정 할인 쿠폰" + i);
            coupon.setInfo(Info.FIX_ALL);
            coupon.setDiscountValue((int)i * 1000);
            coupon.setMember(member);

            couponService.createCoupon(coupon);
        }
    }

    private void initOrders(int repeat) {
        for (int i = 0; i < repeat; i++) {
            initCartItems();
            initOrder();
        }
        initCartItems();
    }

    private void initOrder() {
        Random random = new Random();
        List<Long> couponids = getRandomCouponIds(random);
        List<CartItem> cartItems = memberService.findCartItems();
        setItemsAndProducts(cartItems); //
        List<CartItemResponse> cartItemResponses = memberMapper
                .cartItemsToCartItemResponses(cartItems);
        int fix = memberService
                .discountCartItems(cartItemResponses, couponids);
        CartResponse cartResponse = memberMapper
                .cartItemResponsesToCartResponse(cartItemResponses, fix);

        Payment[] payments = {
            Payment.ACCOUNT_TRANSFER, Payment.CARD, Payment.POINT, Payment.THIRD_PARTY
        };

        Order order = orderMapper.getSaveOrderFromCartResponse(cartResponse,
            payments[random.nextInt(4)]);
        orderService.createOrder(order);
        orderService.addMoneyToMart(cartItems);
        memberService.deleteCartItems();
    }

    private void setItemsAndProducts(List<CartItem> cartItems) {
        for (CartItem cartItem : cartItems) {
            setItem(cartItem);
        }

    }

    private void setItem(CartItem cartItem) {
        Item item = itemService.findItem(cartItem.getItem().getItemId());
        setMart(item);
        setProduct(item);
        cartItem.setItem(item);
    }

    private void setMart(Item item) {
        Mart mart = martService.findMart(item.getMart().getMartId());
        item.setMart(mart);
    }

    private void setProduct(Item item) {
        Product product = productService.findProduct(item.getProduct().getProductId());
        setBrand(product);
        item.setProduct(product);
    }

    private void setBrand(Product product) {
        Brand brand = brandService.findBrand(product.getBrand().getBrandId());
        product.setBrand(brand);
    }

    private List<Long> getRandomCouponIds(Random random) {
        List<Long> couponids = new ArrayList<>();
        boolean[] visits = new boolean[13];

        for (int i = 0; i < 3; i++) {
            visits[random.nextInt(12) + 1] = true;
        }
        for (int i = 1; i < visits.length; i++) {
            if (visits[i]) {
                couponids.add((long)i);
            }
        }
        return couponids;
    }
}
