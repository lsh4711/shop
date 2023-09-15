<template>
  <div class="card border-opacity-25 shadow p-1">
    <RouterLink to="/" class="router-link no-drag" style="color: dimgrey" :ondragstart="blockDrag">
      <img :src="image" class="card-img-top" alt="mart" />
      <div class="card-body">
        <h5 class="card-title" style="font-size: 100%">
          <b>{{ name }}</b>
        </h5>
        <p class="card-text" style="opacity: 0.5; font-size: 50%">{{ address }}</p>
        <!-- <div style="text-align: center">
        <button class="custom-btn btn-1"><span>상품 목록 보기</span></button>
      </div> -->
      </div>
    </RouterLink>
  </div>
</template>

<script setup lang="ts">
import { blockDrag } from "@/utils/button/script/no-drag";

defineProps<{ name: string; address: string; image: string }>();
</script>

<style scoped>
@import "../../../utils/button/css/no-drag.css";
/* @import "../../../utils/button/css/button1.css";

.custom-btn {
  font-size: small;
  width: 100px;
  height: 30px;
  line-height: 31px;
  border-radius: 5px;
} */

.card {
  margin: 25px;
}

.card:hover {
  margin: 0;
}

.router-link {
  text-decoration: none;
}
.router-link:hover {
  color: red;
}
</style>
