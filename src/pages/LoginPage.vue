<template>
  <main class="form-signin w-100 m-auto shadow rounded-4" style="background-color: #e3f2fd">
    <form @submit.prevent="login">
      <img
        class="img-fluid img-thumbnail mb-3"
        src="/images/shop.gif"
        alt="shop"
        style="max-width: 25%; max-height: 75px; cursor: pointer"
        data-bs-dismiss="modal"
      />
      <button
        type="button"
        class="btn-close"
        data-bs-dismiss="modal"
        aria-label="Close"
        style="float: right"
      ></button>
      <h1 class="h3 mb-3 fw-normal">로그인 페이지</h1>
      <div class="form-floating">
        <input
          class="form-control username"
          id="floatingInput"
          placeholder="Userame"
          v-model="username"
          required
        />
        <label for="floatingInput">아이디</label>
      </div>
      <div class="form-floating">
        <input
          type="password"
          class="form-control password"
          id="floatingPassword"
          placeholder="Password"
          v-model="password"
          required
        />
        <label for="floatingPassword">비밀번호</label>
      </div>

      <div class="form-check text-start my-3">
        <input class="form-check-input" type="checkbox" value="remember-me" id="flexCheckDefault" />
        <label class="form-check-label" for="flexCheckDefault">자동 로그인</label>
      </div>
      <button class="btn btn-primary w-100 py-2">로그인</button>
      <button
        type="button"
        class="btn btn-secondary w-100 py-2"
        data-bs-target="#signup-modal"
        data-bs-toggle="modal"
      >
        회원가입
      </button>
      <div class="d-flex mt-3">
        <a
          href="https://github.com/lsh4711"
          style="text-decoration: none; color: black"
          target="_blank"
        >
          <img
            src="/images/github.svg"
            alt="github"
            width="25px"
            height="25px"
            style="margin-bottom: 10px"
          />lsh4711</a
        >
        <span class="text-body-secondary pt-1" style="margin-left: auto">&copy; 2023</span>
      </div>
    </form>
  </main>
</template>

<script setup lang="ts">
import { useSessionStore } from "@/store";
import { MemberApi } from "@/utils/api/member/MemberApi";
import { ref } from "vue";

const store = useSessionStore();

const username = ref("");
const password = ref("");

const login = () =>
  MemberApi.loginMember(username.value, password.value).then((r) => {
    store.isLogin = true;
    store.token = r.headers["authorization"];
  });
</script>

<style scoped>
@import "../utils/member/css/login.css";
</style>
