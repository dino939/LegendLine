package ru.hld.legendline.api.module;

public enum Category
{
    Player("Player", 1), 
    Render("Render", 3), 
    Combat("Combat", 0), 
    Config("Config", 5), 
    Misc("Misc", 4);
    
    private static final Category[] $VALUES;
    
    Movement("Movement", 2);
    
    static {
        $VALUES = new Category[] { Category.Combat, Category.Player, Category.Movement, Category.Render, Category.Misc, Category.Config };
    }
    
    private Category(final String s, final int n) {
    }
}
