<script setup>
    import Footer from "../../components/Footer.vue";
</script>

<template>
    <div class="locator-view">
        <div class="form-container">
            <div class="form-header">
                <h1>Nearest Bin</h1>
                <div class="row">
                    <img src="/src/assets/recycle">
                    Location 1</div>
                <div class="row">
                    Location 2</div>
                <div class="row">
                    Location 3</div>
            </div>
        </div>
    </div>
</template>

<style scoped>

h1 {
    font-weight: 700;
}

label {
    margin: 7px 5px 0;
    font-size: 20px;
    
 
  }
  
    .name-field {
        margin-top: 60px;
    }
    .locator-view {
        text-align: center;
    }
    .form-locate {
        width: 90%;
        margin: 30px auto;
        
    }


    .card {
        background-color: #CEE5D0;
    }
    .btn-check:checked+.btn {
        background-color:#5E454B;
        color: white !important;
    }
    
</style>