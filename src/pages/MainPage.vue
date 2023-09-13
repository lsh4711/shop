<template>
  <div class="cover">
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

.cover {
  background-image: url(images/cover.jpg);
  background-size: cover;
  width: 100%;
  height: 100%;
}
</style>
