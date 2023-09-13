<template>
  <div class="card border-opacity-25 shadow p-1">
    <RouterLink to="/" class="router-link" style="color: dimgrey">
      <img :src="img" class="card-img-top" alt="mart" />
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
