<template>
  <MartCardGrid :marts="marts"></MartCardGrid>
</template>

<script setup lang="ts">
import { MartApi } from "@/utils/api/mart/martApi";
import type { MartResponse } from "@/utils/api/mart/response/MartResponse";
import { ref, type Ref } from "vue";
import MartCardGrid from "../../components/card/mart/MartCardGrid.vue";

let marts: Ref<MartResponse[]> = ref([]);

MartApi.getPublicMarts(1, 50).then((r) => (marts.value = r.data));
</script>

<style scoped></style>
