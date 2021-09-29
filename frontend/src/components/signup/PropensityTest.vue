<template>
    <div>
        <div class="md-layout md-gutter">
            <div class="md-layout-item" v-for="(item, index) in propensity" :key="index">
                <propensity-tab
                    :index="index"
                    :tab-name=item
                    plain
                    flex-column
                    nav-pills-icons
                    color-button="primary"
                ></propensity-tab>    
            </div>
        </div>
    </div>
</template>
<script>
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
    methods : {
        clickPropensity(type){
            return type;
        }
    }
};
</script>

<style lang="scss" scoped>
</style>