<template>
    <md-card
        class="md-card-tabs"
        :class="[
            { 'flex-column': flexColumn },
            { 'nav-pills-icons': navPillsIcons },
            { 'md-card-plain': plain }]">
        <md-card-header slot="header-title"></md-card-header>

        <md-list class="nav-tabs">
            <md-list-item
                v-for="(item, index) in tabName"
                @click="switchPanel(tabName[index])"
                :key="item"
                :class="[
                    { active: isActivePanel(tabName[index]) },
                    { [getColorButton(colorButton)]: isActivePanel(tabName[index]) }
                ]"
                class="md-display-2"
            >
            <h2>{{ tabName[index] }}</h2>
            </md-list-item>
        </md-list>
    </md-card>
</template>

<script>
export default {
    props: {
        flexColumn: Boolean,
        navPillsIcons: Boolean,
        plain: Boolean,
        tabName: Array,
        colorButton: {
            type: String,
            default: ""
        }
    },
    data() {
        return {
            activePanel: this.tabName[0]
        };
    },
    methods: {
        switchPanel(panel) {
            this.activePanel = panel;
        },
        isActivePanel(panel) {
            return this.activePanel == panel;
        },
        getColorButton: function(colorButton) {
            return "md-" + colorButton + "";
        }
    }
};
</script>

<style lang="css">
    .md-list-itme {
        border: 1px;
    }
</style>
