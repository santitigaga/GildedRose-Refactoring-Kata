package com.gildedrose;

class GildedRose {
    private static final String BACKSTAGE_ITEM = "Backstage passes to a TAFKAL80ETC concert";
    private static final String CONJURED_ITEM = "Conjured Mana Cake";
    private static final String AGED_ITEM = "Aged Brie";
    private static final String SULFURAS_ITEM = "Sulfuras, Hand of Ragnaros";
    final Item[] items;


    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {

        for (Item item : items) {
            reduceSell(item);
            switch (item.name) {
                case BACKSTAGE_ITEM:
                    changeQualityBackstage(item);
                    break;
                case SULFURAS_ITEM:
                    break;
                case AGED_ITEM:
                    changeQualityAged(item);
                    break;
                case CONJURED_ITEM:
                    changeQualityConjured(item);
                    break;
                default:
                    changeQualityDefault(item);
                    break;
            }
        }
    }

    private void reduceSell(Item item) {
        if (!item.name.equals(SULFURAS_ITEM)) {
            item.sellIn--;
        }
    }

    public void changeQualityBackstage(Item item) {
        item.quality++;
        if (item.sellIn < 11 && item.quality < 50) {
            item.quality++;
        }
        if (item.sellIn < 6 && item.quality < 50) {
            item.quality++;
        }
        if (item.sellIn < 0) {
            item.quality = 0;
        }
    }

    public void changeQualityAged(Item item) {
        if (item.quality < 50) {
            item.quality++;
        }
    }

    public void changeQualityConjured(Item item) {
        if (item.quality > 2) {
            item.quality = item.quality - 2;
        } else if (item.quality == 1) {
            item.quality--;
        }
    }

    public void changeQualityDefault(Item item) {
        item.quality--;
    }

}