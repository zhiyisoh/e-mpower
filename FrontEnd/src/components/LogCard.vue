<template v-for="logs in groupedLogs" >
    <div class="card-display container row">
        <!-- <li v-for="logs in groupedLogs" class="row"> -->
            <li v-for="log in logs" class="col-lg-4" style="list-style: none;">
                <div class="card h-100">
                    <img class="card-img-top" src="/src/assets/x-LI-image.svg" alt="hehe" id="category-pic" img-top>
                    <div class="card-body">
                        <h5 class="card-title">{{log.itemName}}</h5>
                        <p class="card-text">Type: {{log.itemType}}</p>
                        <RouterLink to="/singlelog"><button type="button" class="btn btn-outline-dark reg-btn btn-lg"
                                href="LogPage.vue">More</button>
                        </RouterLink>
                    </div>
                </div>
            </li>
    </div>
</template>

<script>
// import axios from 'axios';
export default {
    data() {
        return {
            logs: [
                {
                    itemType: "Household Battery",
                    itemName: "Testing",
                    createdDate: '2022-01-20',
                    itemNotes: 'omg'
                },
                {
                    itemType: "Large Household Appliance",
                    itemName: "Rice cooker",
                    createdDate: '2022-02-22',
                    itemNotes: 'i eat rice'
                },
                {
                    itemType: "Large Household Appliance",
                    itemName: "Washing machine",
                    createdDate: '2022-04-20',
                    itemNotes: 'wishy washi'
                },
                {
                    itemType: "Household Battery",
                    itemName: "Testing",
                    createdDate: '2022-06-09',
                    itemNotes: 'omg'
                }
            ]
        };
    },
    // data() {
    //     return {
    //         logs: [],
    //     };
    // },
    // methods: {
    //     async getAllPastLogs() {
    //         try {
    //             const response = LogService.getAllLogs();
    //             this.logs = response.data;
    //         }catch (error){
    //             console.log(error);
    //         }

    computed: {
        groupedLogs() {
            return _.chunk(this.logs, 9)
            // returns a nested array: 
            // [[log, log, log], [log,log, log], ...]
        }
    }
}
</script>

<style>
.card-display {
    margin: 20px 0;
    padding: 7% 15%;
    text-align: centre;
    border-radius: 10%;
}

</style>