<template>
  <h1 style="text-align: center; color: black; margin-top: 10px">상품 목록</h1>
  <h3>
    <b>마트 명: {{ mart.name }}</b>
  </h3>
  <pre>
마트 주소: {{ mart.address }}
등록일: {{ mart.createdAt }}</pre
  >
  <hr size="50" width="80%" style="margin: auto" />
  <ItemListView :martId="parseInt(martId)"></ItemListView>
</template>

<script setup lang="ts">
import { MartApi } from "@/utils/api/mart/MartApi";
import type { MartResponse } from "@/utils/api/mart/response/MartResponse";
import dayjs, { Dayjs } from "dayjs";
import { ref, type Ref } from "vue";
import ItemListView from "../views/item/ItemListView.vue";
const props = defineProps<{
  martId: string;
}>();
let mart: Ref<MartResponse> = ref({} as MartResponse);

MartApi.getPublicMart(parseInt(props.martId)).then((r) => {
  mart.value = r.data;
  mart.value.createdAt = dayjs(mart.value.createdAt).format("YYYY-MM-DD") as unknown as Dayjs;
});
</script>

<style scoped></style>
