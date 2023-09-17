<template>
  <!-- {{ marts }} -->
  <div class="container" style="width: 70%">
    <div class="row">
      <div v-for="item in items" :key="item.itemId" class="col-sm-6 col-md-4 col-lg-3">
        <ItemCard :item="item" :image="image.src"></ItemCard>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { loadImage } from "@/utils/Loading/script/loading";
import type { ItemResponse } from "@/utils/api/item/response/ItemResponse";
import ItemCard from "./ItemCard.vue";

const image = loadImage("/images/shop.gif");
defineProps<{
  items: ItemResponse[];
}>();
</script>

<style scoped></style>
