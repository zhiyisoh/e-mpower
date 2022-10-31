<script setup>
    import Footer from "../../components/Footer.vue";
</script>

<template>
    <div class="locator-view">
        <div class="form-container">
            <div class="container">
                <h1>Nearest Bin</h1>
                <div class="row binentry">
                    <img src="/src/assets/recycle-bin.png" style="max-width: 200px">
                    <div class="item">
                    <h4 >Address 1</h4>
                    <h4>Postal 1</h4>
                    <h4>Accepts:</h4>
                    </div>
                </div>
                <div class="row binentry">
                    <img src="/src/assets/recycle-bin.png" style="max-width: 200px">
                    <div class="item">
                    <h4 >Address 2</h4>
                    <h4>Postal 2</h4>
                    <h4>Accepts:</h4>
                    </div>
                </div>
                <div class="row binentry">
                    <img src="/src/assets/recycle-bin.png" style="max-width: 200px">
                    <div class="item">
                    <h4 >Address 3</h4>
                    <h4>Postal 3</h4>
                    <h4>Accepts:</h4>
                    </div>
                </div>
                <RouterLink to="/binlocator">
                    <button class="btn btn-primary find-button" href="BinLocator.vue">Find Another Bin</button>
                </RouterLink>
                
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

    .binentry {
        padding-top: 60px;
    }
    .locator-view {
        text-align: center;
    }
    .container {
        width: 70%;
        margin: 30px auto;
        
    }

    .item{
        width: 55%;
        text-align: left;
    }
    .find-button {
        margin-left: auto;
        margin-right: 0;
        display: block;
    }
    a{
        text-decoration: none;
    }
    
</style>