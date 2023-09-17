<template>
  <div class="cover" :style="{ backgroundImage: `url(${image.src})` }">
    <div class="position-absolute bottom-0 start-50 translate-middle">
      <RouterLink to="/marts">
        <button class="custom-btn btn-1"><span>쇼핑하러 가기</span></button>
      </RouterLink>
    </div>
  </div>
</template>

<script setup lang="ts">
import { loadImage } from "@/utils/Loading/script/loading";

const image = loadImage("/images/cover.jpg");
</script>

<style scoped>
@import "../utils/button/css/button1.css";

.cover {
  /* background-image: url(/images/cover.jpg); */
  background-size: cover;
  width: 100%;
  height: 100%;
}
</style>
