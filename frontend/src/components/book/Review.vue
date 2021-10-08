<template>
    <div>
        <md-list>
            <md-list-item>
                <div class="md-list-item-text">
                    <div class="md-layout">
                        <div class="md-layout-item md-size-60">
                            <md-field>
                                <label>Review</label>
                                <md-input v-model="review"></md-input>
                            </md-field>
                        </div>
                        <div class="md-layout-item">
                            <md-field>
                                <label>Score</label>
                                <md-input v-model="score" type="number"></md-input>
                            </md-field>
                        </div>
                        <div class="md-layout-item">
                            <span class="material-icons">done</span>
                        </div>
                    </div>
                    
                    
                </div>
            </md-list-item>
            <md-list-item>
                <div class="md-list-item-text">
                    <span>Ali Connors 작성 날짜</span><span class="material-icons">star star star star star</span>
                    <p>I'll be in your neighborhood doing errands this week. Do you want to meet?</p>
                </div>
            </md-list-item>
        </md-list>
    </div>
</template>

<script>
export default {
    data() {
        return {
            review : "",
            score : 0
        }
    },
}
</script>

<style lang="scss">
.score {
    z-index : auto;
}
</style>