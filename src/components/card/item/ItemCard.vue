<template>
  <div class="card border-opacity-25 shadow p-1">
    <RouterLink :to="'/'" append class="router-link" style="color: dimgrey">
      <img :src="image" class="card-img-top" alt="mart" />
      <div class="card-body">
        <h5 class="card-title" style="font-size: 100%">
          <b>{{ item.productName }}</b>
        </h5>
        <p class="card-text" style="opacity: 0.5; font-size: 50%">{{ item.price }}원</p>
      </div>
    </RouterLink>
  </div>
</template>

<script setup lang="ts">
import type { ItemResponse } from "@/utils/api/item/response/ItemResponse";

defineProps<{ item: ItemResponse; image: string }>();
</script>

<style scoped>
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
