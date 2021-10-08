<template>
    <div class="wrapper">
        <carousel/>
        <book-detail v-if="getMainPageInfo.modal" />
    </div>
</template>

<script>
import BookDetail from '../components/book/BookDetail.vue'
import { mapGetters } from 'vuex';
import Carousel from '../components/book/Carousel.vue';
export default {
    components: { BookDetail, Carousel },
    props: {
        header: {
        type: String,
        default: require("@/assets/img/library.jpg")
        }
    },
    data() {
        return {
        }
    },
    computed : {
        headerStyle() {
            return {
                backgroundImage: `url(${this.header})`
            };
        },
        ...mapGetters(['getMainPageInfo'])
    },
    methods : {
        openModal : function(){
            this.$store.dispatch('mainModalFlag', true);
        },
    }
}
</script >

<style>
</style>