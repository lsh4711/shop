<template>
  <main class="form-signup w-100 m-auto shadow rounded-4" style="background-color: #e3f2fd">
    <form @submit.prevent="signup">
      <img
        class="img-fluid img-thumbnail mb-3"
        src="/images/shop.gif"
        alt="shop"
        style="max-width: 25%; max-height: 75px; cursor: pointer"
        data-bs-dismiss="modal"
      />
      <button
        id="signup-close"
        type="button"
        class="btn-close"
        data-bs-dismiss="modal"
        aria-label="Close"
        style="float: right"
      ></button>
      <h1 class="h3 mb-3 fw-normal">회원가입 페이지</h1>
      <div class="form-floating">
        <input
          class="form-control username"
          id="floatingInput"
          placeholder="Username"
          v-model="username"
          maxlength="16"
          title="아이디는 공백을 제외한 16자 이하의 문자열이어야 합니다."
          :pattern="'^\\S{1,16}$'"
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
          minlength="8"
          maxlength="16"
          title="비밀번호는 공백을 제외한 8~16자 영문, 숫자, 특수문자 조합이어야 합니다."
          :pattern="'(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}'"
          required
        />
        <label for="floatingPassword">비밀번호</label>
      </div>
      <div class="form-floating">
        <input
          type="password"
          class="form-control password-confirm"
          id="floatingPassword"
          placeholder="Password"
          minlength="8"
          maxlength="16"
          title="비밀번호는 공백을 제외한 8~16자 영문, 숫자, 특수문자 조합이어야 합니다."
          :pattern="'(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}'"
          required
        />
        <label for="floatingPassword">비밀번호 확인</label>
      </div>

      <button
        type="button"
        class="btn btn-primary w-100 py-2"
        data-bs-target="#login-modal"
        data-bs-toggle="modal"
      >
        로그인
      </button>
      <button class="btn btn-secondary w-100 py-2">회원가입</button>
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

const username = ref("");
const password = ref("");

const store = useSessionStore();

const signup = () =>
  MemberApi.postMember(username.value, password.value).then(() => {
    document.getElementById("signup-close")?.click();
    MemberApi.loginMember(username.value, password.value).then((r) => {
      store.isLogin = true;
      store.token = r.headers["authorization"];
    });
  });
</script>

<style scoped>
@import "../utils/member/css/signup.css";
</style>
