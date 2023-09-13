<template>
  <h1 style="text-align: center; color: black; margin-top: 10px">마트 목록</h1>
  <hr size="50" width="80%" style="margin: auto; margin-top: 50px" />
  <MartListView></MartListView>
</template>

<script setup lang="ts">
import MartListView from "../views/mart/MartListView.vue";

import { isLoading } from "@/main";
import { onMounted } from "vue";

onMounted(() => {
  isLoading.value = false;
});
</script>

<style scoped></style>
