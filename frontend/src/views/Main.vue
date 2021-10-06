<template>
    <div>
        <h1>메인</h1>
        <book-detail v-if="getMainPageInfo.modal" />
        <md-button v-if="!getMainPageInfo.modal" class="md-raised md-simple md-lg md-success" @click="openModal">OpenModal</md-button>
    </div>
</template>

<script>
import BookDetail from '../components/book/BookDetail.vue'
import { mapGetters } from 'vuex';
export default {
    components: { BookDetail },
    computed : {
        ...mapGetters(['getMainPageInfo'])
    },
    methods : {
        openModal : function(){
            this.$store.dispatch('mainModalFlag', true);
        }
    }
}
</script>

<style>

</style>