<template>
    <div class = "information">
        <h1>Date:</h1>
        <h1>Type:</h1>

        <div class = "btn-toolbar">
            <RouterLink to="/editlog"><button type="button" class="btn btn-primary enterlog-btn btn-lg"
                href="EnterLog.vue">Edit</button></RouterLink>
            <button v-on:click="" type="button" class="btn btn-outline-dark reg-btn btn-lg">Delete</button>
        </div>
        
    </div>    
</template>

<style>

    .information {
    display: inline-block;
    width: 550px;
    margin: 20px 40px;
    text-align: left;
    }

</style>