package com.shop.global.init;

import javax.annotation.PostConstruct;

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
import com.shop.domain.item.service.ItemService;
import com.shop.domain.mart.entity.Mart;
import com.shop.domain.mart.service.MartService;
import com.shop.domain.member.entity.CartItem;
import com.shop.domain.member.entity.Member;
import com.shop.domain.member.service.MemberService;
import com.shop.domain.product.entity.Product;
import com.shop.domain.product.entity.ProductCategory;
import com.shop.domain.product.service.ProductCategoryService;
import com.shop.domain.product.service.ProductService;
import com.shop.global.auth.JwtAuthenticationToken;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class Init {
    private final MemberService memberService;

    private final MartService martService;

    private final BrandService brandService;

    private final CategoryService categoryService;

    private final ProductService productService;

    private final ProductCategoryService productcategoryService;

    private final ItemService itemService;

    private final CouponService couponService;

    @PostConstruct
    private void init() {
        Authentication authentication = new JwtAuthenticationToken(1, null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Member
        for (int i = 1; i <= 5; i++) {
            String encryptedPassword = memberService.encryptPassword("member" + i);
            Member member = new Member();
            member.setUsername("member" + i);
            member.setPassword(encryptedPassword);
            member.setAddress("무슨동 무슨길 " + i);
            // member.setRole(Role.SELLER);

            memberService.createMember(member);
        }

        // Mart
        String[] martNames = {"동네 마트", "이마트", "농림 마트", "농협 마트", "편의점"};
        for (int i = 1; i <= 5; i++) {
            Mart mart = new Mart();
            mart.setName(martNames[i - 1]);
            mart.setAddress("무슨동 무슨길 " + i);

            Member member = new Member();
            member.setMemberId(1L);
            mart.setMember(member);

            martService.createMart(mart);
        }

        // Brand
        String[] brandNames = {"농심", "롯데", "동원", "오뚜기", "풀무원"};
        for (int i = 1; i <= 5; i++) {
            Brand brand = new Brand();
            brand.setName(brandNames[i - 1]);
            brand.setAddress("무슨동 무슨길 " + i);

            brandService.createBrand(brand);
        }

        // Category
        String[] categoryNames = {"음료", "채소", "해산물", "육류", "유제품"};
        for (int i = 1; i <= 5; i++) {
            Category category = new Category();
            category.setName(categoryNames[i - 1]);
            categoryService.creatCategory(category);
        }

        // Product
        String[] productNames = {
            "아침 사과 주스 200mL", "찌개용 한입 두부 200g", "마늘 훈제 닭고기 300g", "멸치 육수 1L", "민트 초코 우유 500mL"
        };
        for (long i = 1; i <= 5; i++) {
            Product product = new Product();
            product.setName(productNames[(int)i - 1]);
            product.setBarcode(Long.toString(i).repeat(8));

            Brand brand = new Brand();
            brand.setBrandId(i);
            product.setBrand(brand);

            productService.createProduct(product);

            for (long j = 1; j <= 3; j++) {
                Category category = new Category();
                category.setCategoryId(j);

                ProductCategory productCategory = new ProductCategory(null, product, category);

                productcategoryService.createProductCategory(productCategory);
            }
        }

        // Item
        for (long i = 1; i <= 5; i++) {
            Mart mart = new Mart();
            mart.setMartId(1L);

            Product product = new Product();
            product.setProductId(i);

            Item item = new Item();
            item.setMart(mart);
            item.setProduct(product);
            item.setPrice(i * 1000);

            itemService.createItem(item);
        }

        // CartItem
        for (long i = 1; i <= 5; i++) {
            Member member = new Member();
            member.setMemberId(1L);

            Item item = new Item();
            item.setItemId(i);

            CartItem cartItem = new CartItem();
            cartItem.setAmount(60 - i * 10);
            cartItem.setMember(member);
            cartItem.setItem(item);

            memberService.addCartItem(cartItem);
        }

        // Coupon
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
}
