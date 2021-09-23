<template>
    <div>
        <div class="md-layout md-gutter">
            <div class="md-layout-item" v-for="(item, index) in propensity" :key="index">
                <propensity-tab
                    :tab-name=item
                    plain
                    flex-column
                    nav-pills-icons
                    color-button="primary"
                ></propensity-tab>    
            </div>
            <!-- <div class="md-layout-item">
                <propensity-tab
                    :tab-name="['I', 'E']"
                    plain
                    flex-column
                    nav-pills-icons
                    color-button="primary"
                ></propensity-tab>   
            </div> -->
        </div>
        <!-- <div class="md-layout md-gutter">
            <div class="md-layout-item md-display-4">E</div>
            <div class="md-layout-item md-display-4">N</div>
            <div class="md-layout-item md-display-4">F</div>
            <div class="md-layout-item md-display-4">P</div>
        </div> -->
        <!-- <span>{{getPropensity}}</span> -->
    </div>
</template>
<script>
import { mapGetters } from 'vuex';
import PropensityTab from './PropensityTab.vue';

export default {
    components: {
        PropensityTab
    },
    data() {
        return {
            propensity : [['I', 'E'], ['S', 'N'], ['T', 'F'], ['J', 'P']]
        }
    },
    computed : {
        ...mapGetters(['getPropensity'])
    },
    methods : {
        clickPropensity(type){
            return type;
        }
    }
};
</script>

<style lang="scss" scoped>
.md-layout-item {
    border-radius: 16px;
    color : gray !important;
}
.click {
    color : white !important;
    background-color: #0097a7 !important;
    // border-radius: 50%!important;
}
</style>