export interface ShoppingCart {
    username: string;
    milksInCart: Array<{id: number,
                      type: string,
                      flavor: string,
                      volume: number,
                      quantity: number,
                      price: number,
                      imageUrl: string}>;
    milksInCartQuantity: Array<number>;
  }