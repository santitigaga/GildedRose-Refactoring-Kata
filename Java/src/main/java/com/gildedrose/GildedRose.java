package com.gildedrose;

class GildedRose {
    private static final int MAX_DEFAULT_QUALITY = 50;
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
            if (isEspecial(item)) {
                value(item);
            } else {
                devalue(item);
            }
            if (item.sellIn < 0) {
                alterQualityWhenDaysAreOver(item);
            }
        }
    }

    private void value(Item item) {
        if (canIncreaseQuality(item.quality)) {
            if (item.name.equals(BACKSTAGE_ITEM)) {
                increaseValueBackstage(item);
            } else {
                increaseQuality(item);
            }
        }
    }

    private void devalue(Item item) {
        if (item.name.equals(CONJURED_ITEM)) {
            reduceQuality(item, 2);
        } else {
            reduceQuality(item, 1);
        }
    }

    private void alterQualityWhenDaysAreOver(Item item) {
        if (item.name.equals(AGED_ITEM) && canIncreaseQuality(item.quality)) {
            increaseQuality(item);
        } else if (item.name.equals(BACKSTAGE_ITEM)) {
            item.quality = 0;
        } else {
            if (item.quality > 0 && !item.name.equals(SULFURAS_ITEM)) {
                reduceQuality(item, 1);
            }
        }
    }

    private void reduceSell(Item item) {
        if (!item.name.equals(SULFURAS_ITEM)) {
            item.sellIn --;
        }
    }

    private void increaseValueBackstage(Item item) {
        increaseQuality(item);
        if (item.sellIn < 11 && canIncreaseQuality(item.quality)) {
            increaseQuality(item);
        }
        if (item.sellIn < 6 && canIncreaseQuality(item.quality)) {
            increaseQuality(item);
        }
    }

    private void increaseQuality(Item item) {
        item.quality ++;
    }

    private boolean canIncreaseQuality(int quality) {
        return quality < MAX_DEFAULT_QUALITY;
    }

    private boolean isEspecial(Item item) {
        return item.name.equals(AGED_ITEM)
                || item.name.equals(BACKSTAGE_ITEM) || item.name.equals(SULFURAS_ITEM);
    }

    private void reduceQuality(Item item, int decrement) {
        if (item.quality > 0) {
            item.quality = item.quality - decrement;
        }
    }
}