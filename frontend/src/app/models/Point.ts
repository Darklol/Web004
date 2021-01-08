export class Point {

    constructor(
        public id: number,
        public x: number,
        public y: number,
        public r: number,
        public inArea: boolean,
        public queryTime: Date) {
    }
}
