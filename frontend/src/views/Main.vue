<template>
    <div class="wrapper">
        
        <carousel v-for="(book, index) in getMainPageInfo.book" v-bind:key="book" :index=index />
        <book-detail v-if="getMainPageInfo.modal" />
    </div>
</template>

<script>
import BookDetail from '../components/book/BookDetail.vue'
import { mapGetters } from 'vuex';
import Carousel from '../components/book/Carousel.vue';
export default {
    components: { BookDetail, Carousel },
    created() {
        this.$store.dispatch('setBookInfo');
    },
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