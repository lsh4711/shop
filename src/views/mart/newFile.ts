import { MartApi } from "@/utils/api/mart/MartApi";
import { onMounted } from "vue";
import { tmp } from "./MartListView.vue";

onMounted(async () => {
const test = await MartApi.getPublicMarts();

tmp.value = test;
// tmp.value = 5;
});
