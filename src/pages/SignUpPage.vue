<template>
  <main class="form-signin w-100 m-auto shadow rounded-4" style="background-color: #e3f2fd">
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
    <h1 class="h3 mb-3 fw-normal">회원가입 페이지</h1>
    <div class="form-floating">
      <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com" />
      <label for="floatingInput">이메일 주소</label>
    </div>
    <div class="form-floating">
      <input
        type="password"
        class="form-control"
        id="floatingInput"
        placeholder="name@example.com"
      />
      <label for="floatingInput">비밀번호</label>
    </div>
    <div class="form-floating">
      <input
        type="password-confirm"
        class="form-control"
        id="floatingPassword"
        placeholder="Password"
      />
      <label for="floatingPassword">비밀번호 확인</label>
    </div>

    <button class="btn btn-primary w-100 py-2" data-bs-target="#login-modal" data-bs-toggle="modal">
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
  </main>
</template>

<script setup lang="ts"></script>

<style scoped>
@import "../utils/signup/css/signup.css";
</style>
