<template>
  <div
    style="
      background-image: url(images/cover.jpg);
      background-size: cover;
      width: 100%;
      height: 100%;
    "
  >
    <div class="position-absolute bottom-0 start-50 translate-middle">
      <RouterLink to="/marts">
        <button class="custom-btn btn-1"><span>쇼핑하러 가기</span></button>
      </RouterLink>
    </div>
  </div>
</template>

<script setup lang="ts"></script>

<style scoped>
@import "../utils/css/button/button1.css";

.btn {
  background-color: #61b0e8;
}
.btn:hover {
  background-color: #3b9ce1;
  /* border-color: #0d6efd; */
  padding: 10px 16px;
  font-size: 18px;
  line-height: 1.3333333;
  border-radius: 6px;
}
</style>
