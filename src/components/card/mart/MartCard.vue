<template>
  <div class="card border-opacity-25 shadow p-1">
    <RouterLink :to="`/marts/${mart.martId}/items`" class="router-link" style="color: dimgrey">
      <img :src="image.src" class="card-img-top" alt="mart" />
      <div class="card-body" style="padding-bottom: 0px">
        <h5 class="card-title" style="font-size: 100%">
          <b>{{ mart.name }}</b>
        </h5>
        <p class="card-text" style="opacity: 0.5; font-size: 50%; margin-bottom: 0px">
          {{ mart.address }}
        </p>
        <div style="text-align: center; margin: -5px">
          <span class="hidden-text" :id="`custom${mart.martId}`">상품 보기</span>
        </div>
      </div>
    </RouterLink>
  </div>
</template>

<script setup lang="ts">
import { loadImage } from "@/utils/Loading/script/loading";
import type { MartResponse } from "@/utils/api/mart/response/MartResponse";
import { onBeforeMount } from "vue";

let image: HTMLImageElement;

onBeforeMount(() => (image = loadImage("/images/shop.gif")));

defineProps<{ mart: MartResponse }>();
</script>

<style scoped>
@import "../../../utils/button/css/button1.css";

.hidden-text {
  color: #61b0e8;
  font-size: 12px;
  font-weight: 500;
  opacity: 0;
  text-shadow: 1px 1px 2px #e3f2fd;
}

.card {
  margin: 25px;
}

.card:hover {
  margin: 0px;
  /* transform: scale(1.2); */
  transition: 0.25s ease-out;
}

.card:not(:hover) {
  transition: 0.25s ease-out;
}

.card:hover .hidden-text {
  font-size: 15px;
  /* transition-delay: 0.25s; */
  transition-duration: 0.25s;
  opacity: 1;
  transition-timing-function: ease-in;
}

.router-link {
  text-decoration: none;
}
.router-link:hover {
  /* color: red; */
}
</style>
