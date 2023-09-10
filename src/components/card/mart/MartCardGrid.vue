<template>
  <div className="container">
    <div className="row">
      <div className="col">
        <MartCard
          :name="'제목fklsdjf'"
          :address="'내용'"
          :img="'../../../public/img2.gif'"
        ></MartCard>
      </div>
      <div className="col">
        <MartCard
          :name="'제목fklsdjf'"
          :address="'내용'"
          :img="'../../../public/img2.gif'"
        ></MartCard>
      </div>
      <div className="col">
        <MartCard
          :name="'제목fklsdjf'"
          :address="'내용'"
          :img="'../../../public/img2.gif'"
        ></MartCard>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import MartCard from "./MartCard.vue";

defineProps(["marts"]);
</script>

<style scoped></style>
