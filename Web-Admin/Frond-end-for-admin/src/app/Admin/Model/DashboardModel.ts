export class DashboardModel {


    constructor(
        public totalUsers: number,
        public totalCategories: number,
        public totalProducts: number,
        public totalOrders: number,
        public totalPendingOrders: number,
        public totalProcessingOrders: number,
        public totalCompletedOrders: number,
        public totalCancelledOrders: number
    ) { }

    get getTotalUsers() {
        return this.totalUsers;
    }

    set setTotalUsers(totalUsers: number) {
        this.totalUsers = totalUsers;
    }

    get getTotalCategories() {
        return this.totalCategories;
    }

    set setTotalCategories(totalCategories: number) {
        this.totalCategories = totalCategories;
    }

    get getTotalProducts() {
        return this.totalProducts;
    }

    set setTotalProducts(totalProducts: number) {
        this.totalProducts = totalProducts;
    }

    get getTotalOrders() {
        return this.totalOrders;
    }

    set setTotalOrders(totalOrders: number) {
        this.totalOrders = totalOrders;
    }

    get getTotalPendingOrders() {
        return this.totalPendingOrders;
    }

    set setTotalPendingOrders(totalPendingOrders: number) {
        this.totalPendingOrders = totalPendingOrders;
    }

    get getTotalProcessingOrders() {
        return this.totalProcessingOrders;
    }

    set setTotalProcessingOrders(totalProcessingOrders: number) {
        this.totalProcessingOrders = totalProcessingOrders;
    }

    get getTotalCompletedOrders() {
        return this.totalCompletedOrders;
    }

    set setTotalCompletedOrders(totalCompletedOrders: number) {
        this.totalCompletedOrders = totalCompletedOrders;
    }

    get getTotalCancelledOrders() {
        return this.totalCancelledOrders;
    }

    set setTotalCancelledOrders(totalCancelledOrders: number) {
        this.totalCancelledOrders = totalCancelledOrders;
    }

    public toString() {
        return "DashboardModel [totalUsers=" + this.totalUsers + ", totalCategories=" + this.totalCategories + ", totalProducts=" + this.totalProducts + ", totalOrders=" + this.totalOrders + ", totalPendingOrders=" + this.totalPendingOrders + ", totalProcessingOrders=" + this.totalProcessingOrders + ", totalCompletedOrders=" + this.totalCompletedOrders + ", totalCancelledOrders=" + this.totalCancelledOrders + "]";
    }
}