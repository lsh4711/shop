<template>
  <p>마트 목록</p>
  <MartCardGrid :marts="marts"></MartCardGrid>
</template>

<script setup lang="ts">
import { getPublicMarts } from "@/utils/api/mart/MartApi";
import MartCardGrid from "../../components/card/mart/MartCardGrid.vue";

const marts = getPublicMarts().data;
</script>

<style scoped></style>
