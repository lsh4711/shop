<template>
  <p>마트 목록</p>
  <!-- <MartCardGrid :marts="marts"></MartCardGrid> -->
  {{ tmp }}
</template>

<script setup lang="ts">
import { MartApi } from "@/utils/api/mart/MartApi";
import { onMounted, ref } from "vue";

let tmp = ref();
onMounted(async () => {
  const test = await MartApi.getPublicMarts(1, 5);

  tmp.value = test.data;
  // tmp.value = 5;
});
</script>

<style scoped></style>
