<template>
  <!-- {{ marts }} -->
  <div class="container" style="width: 70%">
    <div class="row">
      <div v-for="mart in marts" :key="mart.martId" class="col-sm-6 col-md-4 col-lg-3">
        <MartCard :name="mart.name" :address="mart.address"></MartCard>
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
