<template>
    <login-card>
        <md-field slot="inputs" :class="[!getSignupCheck.id.condition.flag || !getSignupCheck.id.duplicate.flag ? 'md-error' : 'md-valid']">
            <md-icon>face</md-icon>
            <label>ID</label>
            <md-input v-model="id" type="text" @keyup="duplicateCheck('id')"></md-input>
            <template v-if="getSignupCheck.id.condition.flag && getSignupCheck.id.duplicate.flag">
                <md-icon>done</md-icon>
                
            </template>
            <template v-else-if="getSignupCheck.id.condition.flag === false">
                <span class="md-helper-text">{{getSignupCheck.id.condition.msg}}</span>
                <md-icon>clear</md-icon>
            </template>
            <template v-else>
                <span class="md-helper-text">{{getSignupCheck.id.duplicate.msg}}</span>
                <md-icon>clear</md-icon>
            </template>
        </md-field>

        <md-field slot="inputs" :class="[!getSignupCheck.nickname.condition.flag || !getSignupCheck.nickname.duplicate.flag ? 'md-error' : 'md-valid']">
            <md-icon>supervisor_account</md-icon>
            <label>NickName</label>
            <md-input v-model="nickname" type="text" @keyup="duplicateCheck('nickname')"></md-input>
            <template v-if="getSignupCheck.nickname.condition.flag && getSignupCheck.nickname.duplicate.flag">
                <md-icon>done</md-icon>
                
            </template>
            <template v-else-if="getSignupCheck.nickname.condition.flag === false">
                <span class="md-helper-text">{{getSignupCheck.nickname.condition.msg}}</span>
                <md-icon>clear</md-icon>
            </template>
            <template v-else>
                <span class="md-helper-text">{{getSignupCheck.nickname.duplicate.msg}}</span>
                <md-icon>clear</md-icon>
            </template>
        </md-field>

        <md-field slot="inputs" :class="[getSignupCheck.password.condition.flag ? 'md-valid' : 'md-error']">
            <md-icon>lock</md-icon>
            <label>Password</label>
            <md-input v-model="password" type="password" @keyup="duplicateCheck('password')"></md-input>
            <span class="md-helper-text">{{getSignupCheck.password.condition.msg}}</span>
            <div v-if="getSignupCheck.password.condition.flag">
                <md-icon>done</md-icon>
            </div>
            <div v-else>
                <md-icon>clear</md-icon>
            </div>
        </md-field>

        <md-field slot="inputs" :class="[getSignupCheck.password.condition.flag && checkPassword() ? 'md-valid' : 'md-error']">
            <md-icon>lock</md-icon>
            <label>PasswordCheck</label>
            <md-input v-model="passwordCheck" type="password" @keyup="checkPassword()"></md-input>
            <span class="md-helper-text"></span>
            <div v-if="getSignupCheck.password.condition.flag && checkPassword()">
                <md-icon>done</md-icon>
            </div>
            <div v-else>
                <md-icon>clear</md-icon>
            </div>
        </md-field>
    </login-card>
</template>

<script>
import { LoginCard } from "@/components";
import { mapGetters } from 'vuex';

export default {
    components: {
        LoginCard
    },
    bodyClass: "login-page",
    data() {
        return {
            id: "",
            nickname: "",
            password: "",
            passwordCheck : ""
        };
    },
    props: {
        header: {
        type: String,
        default: require("@/assets/img/profile_city.jpg")
        }
    },
    computed: {
        headerStyle() {
            return {
                backgroundImage: `url(${this.header})`
            };
        },
        messageClass () {
            return {
                'md-invalid' : !this.getSignupCheck.password.condition.flag
            }
        },
        ...mapGetters(['getSignupCheck'])
    },
    methods : {
        conditionCheck : function(type){
            if(type === 'id'){ // ID 조건
                return this.id.length > 3 ? true : false
            }else if(type === 'nickname'){ // 닉네임 조건
                return this.nickname.length > 2 ? true : false
            }else{ // Password 조건
                return this.password.length > 5 ? true : false
            }
        },
        duplicateCheck : function(type){
            var data = {
                condition : this.conditionCheck(type),
                val : type,
            };
            if(type === 'id'){
                data["content"] = this.id;
            }else {
                data["content"] = this.nickname
            }
            this.$store.dispatch('signupCheck', data);
        },
        checkPassword : function(){
            if(this.password === this.passwordCheck){
                this.$store.dispatch('passwordCheck', true);
                return true;
            }
            this.$store.dispatch('passwordCheck', false); 
            return false;
        }
    }
};
</script>

<style lang="css"></style>
