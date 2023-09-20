<template>
  <!-- <ItemCardGrid :items="items"></ItemCardGrid> -->
  <ItemTable :items="items"></ItemTable>
</template>

<script setup lang="ts">
import { ItemApi } from "@/utils/api/item/itemApi";
import type { ItemResponse } from "@/utils/api/item/response/ItemResponse";
import { ref, type Ref } from "vue";
import ItemTable from "../../components/table/ItemTable.vue";

const props = defineProps<{
  martId: number;
}>();

let items: Ref<ItemResponse[]> = ref([]);

ItemApi.getItems(props.martId).then((r) => (items.value = r.data));
</script>

<style scoped></style>
