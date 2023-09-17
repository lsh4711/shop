<template>
  <tr>
    <th scope="row">{{ item.itemId }}</th>
    <td class="w-25">
      <img
        :src="image.src"
        class="img-fluid img-thumbnail"
        alt="item"
        style="max-width: 50%; max-height: 100px"
      />
    </td>
    <td>{{ item.productName }}</td>
    <td>{{ item.price.toLocaleString("ko-KR") }}원</td>
    <td>{{ categoryNames.join(", ") }}</td>
  </tr>
</template>

<script setup lang="ts">
import { serverApiUrl } from "@/main";
import { loadImage } from "@/utils/Loading/script/loading";
import type { ItemResponse } from "@/utils/api/item/response/ItemResponse";

const props = defineProps<{
  item: ItemResponse;
}>();

const image = loadImage(`${serverApiUrl}/products/${props.item.productId}/images/thumbnail`);
const categoryNames = props.item.categories.map((category) => category.name);
</script>

<style scoped></style>
