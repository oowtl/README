<template>
<table>
    <thead>
        <tr>
            <th>
                BookId
            </th>
            <th>
                BookName
            </th>
            <th>
                Options
            </th>
        </tr>
        <tbody>
            <tr v-for="book in books" v-bind:key="book">
                <td>{{book.BookId}}</td>
                <td>{{book.BookName}}</td>
                <td>
                    <button type="button"> 수정</button>
                    <button type="button"> 삭제</button>
                </td>
            </tr>
        </tbody>
    </thead>
</table>
</template>

<script>
import axios from 'axios'

export default {
  components: {


  },
  name: "index",
  data() {
      return {
          books:[]
      };
  },
  methods: {
      refreshData() {
          axios.get("http://127.0.0.1:8000/book") 
          .then((response)=>{
              this.books=response.data;
          })
      }
  },
  mounted:function() {
      this.refreshData();
  },
};

</script>

