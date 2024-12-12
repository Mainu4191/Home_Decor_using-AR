export class Products {
    constructor(
        public id: number,
        public name: string,
        public description: string,
        public price: string,
        public productCategoryID: string,
        public image: string,
        public model: string,
        public modelBufferFile: string
    ) { }
}