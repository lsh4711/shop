<template>
  <div class="card" style="width: 15rem">
    <img :src="img" class="card-img-top" alt="..." />
    <div class="card-body">
      <h5 class="card-title">{{ name }}</h5>
      <p class="card-text">{{ address }}</p>
      <a href="#" class="btn btn-primary">Go somewhere</a>
    </div>
  </div>
</template>

<script setup lang="ts">
defineProps({ name: String, address: String, img: String });
</script>

<style scoped></style>
