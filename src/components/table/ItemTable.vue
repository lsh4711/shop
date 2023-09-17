<template>
  <div class="container">
    <div class="row">
      <div class="col-12">
        <table class="table table-image shadow rounded-4 overflow-hidden table-hover">
          <thead>
            <tr>
              <th scope="col">상품 ID</th>
              <th scope="col">상품 이미지</th>
              <th scope="col">제품 명</th>
              <th scope="col">가격</th>
              <th scope="col">제품 분류</th>
            </tr>
          </thead>
          <tbody>
            <ItemTableElement
              v-for="item in items"
              :key="item.itemId"
              :item="item"
            ></ItemTableElement>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>
<!-- src="https://gdimg.gmarket.co.kr/2841366690/still/600?ver=1678759701" -->
<!-- src="https://img-cf.kurly.com/shop/data/goodsview/20221216/gv10000467141_1.jpg" -->

<script setup lang="ts">
import type { ItemResponse } from "@/utils/api/item/response/ItemResponse";
import ItemTableElement from "./ItemTableElement.vue";

defineProps<{
  items: ItemResponse[];
}>();
</script>

<style scoped>
/* .table th {
  padding-left: 5px;
}
.table th.rowheader {
  padding-left: 100px;
} */
.container {
  padding: 2rem 0rem;
}

h4 {
  margin: 2rem 0rem 1rem;
}

.table-image {
  td,
  th {
    vertical-align: middle;
  }
}
</style>
