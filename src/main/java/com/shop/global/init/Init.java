package com.shop.global.init;

import javax.annotation.PostConstruct;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import com.shop.domain.brand.entity.Brand;
import com.shop.domain.brand.service.BrandService;
import com.shop.domain.category.entity.Category;
import com.shop.domain.category.service.CategoryService;
import com.shop.domain.mart.entity.Mart;
import com.shop.domain.mart.service.MartService;
import com.shop.domain.member.entity.Member;
import com.shop.domain.member.entity.Member.Role;
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
            member.setRole(Role.SELLER);

            memberService.createMember(member);
        }

        // Mart
        for (int i = 1; i <= 5; i++) {
            Mart mart = new Mart();
            mart.setName("마트" + i);
            mart.setAddress("무슨동 무슨길 " + i);

            Member member = new Member();
            member.setMemberId(1L);
            mart.setMember(member);

            martService.createMart(mart);
        }

        // Brand
        for (int i = 1; i <= 5; i++) {
            Brand brand = new Brand();
            brand.setName("브랜드" + i);
            brand.setAddress("무슨동 무슨길 " + i);

            brandService.createBrand(brand);
        }

        // Category
        for (int i = 1; i <= 5; i++) {
            Category category = new Category();
            category.setName("분류 명" + i);
            categoryService.creatCategory(category);
        }

        // Product
        for (int i = 1; i <= 5; i++) {
            Product product = new Product();
            product.setName("제품 이름" + i);
            product.setCode(Integer.toString(i).repeat(8));

            Brand brand = new Brand();
            brand.setBrandId(1L);
            product.setBrand(brand);

            productService.createProduct(product);

            for (long j = 1; j <= 3; j++) {
                Category category = new Category();
                category.setCategoryId(j);

                ProductCategory productCategory = new ProductCategory(null, product, category);

                productcategoryService.createProductCategory(productCategory);
            }
        }
    }
}
