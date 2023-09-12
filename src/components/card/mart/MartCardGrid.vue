<template>
  <!-- {{ marts }} -->
  <div class="container">
    <div class="row">
      <div v-for="mart in marts" :key="mart.martId" class="col-sm-6 col-md-4 col-lg-3">
        <MartCard
          :name="mart.name"
          :address="mart.address"
          :img="'../../../public/img2.gif'"
        ></MartCard>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import type { MartResponse } from "@/utils/api/mart/response/MartResponse";
import { computed } from "vue";
import MartCard from "./MartCard.vue";
const props = defineProps<{
  marts: MartResponse[];
}>();
const marts = computed(() => props.marts);
</script>

<style scoped></style>
