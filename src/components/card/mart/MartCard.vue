<template>
  <div class="card mb-5 border-opacity-25" style="width: 200px">
    <!-- <RouterLink to="/" > -->
    <img :src="img" class="card-img-top" alt="mart" />
    <div class="card-body">
      <h5 class="card-title">
        <b>{{ name }}</b>
      </h5>
      <p class="card-text" style="opacity: 0.5">{{ address }}</p>
      <div style="text-align: center">
        <button class="custom-btn btn-1"><span>상품 목록 보기</span></button>
      </div>
    </div>
    <!-- </RouterLink> -->
  </div>
</template>

<script setup lang="ts">
defineProps({ name: String, address: String, img: String });
</script>

<style scoped>
@import "../../../utils/css/button/button1.css";

.custom-btn {
  font-size: small;
  width: 100px;
  height: 30px;
  line-height: 31px;
  border-radius: 5px;
}

.card:hover {
  border-width: 20px;
  border-radius: 10px;
  border-color: #f1f7fd;
}

.router-link {
  text-decoration: none;
}
.router-link:hover {
  /* border-width: 10px; */
  text-decoration: underline;
  text-decoration-thickness: 1.5px;
  text-underline-offset: 3px;
  color: lightgray;
}
</style>
