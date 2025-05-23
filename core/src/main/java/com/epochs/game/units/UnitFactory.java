//Deprecated

//package com.epochs.game.units;
//
//import com.badlogic.gdx.graphics.Color;
//
//public class UnitFactory {
//
//    public static Unit createUnit(String type, float x, float y) {
//        switch (type) {
//            case "MaceWarrior": // Wojownik z maczugą
//                return new Unit("Wojownik z maczugą", 15, 1, 10, 30, 40, 15, 10, x, y, 50, 50, Color.BROWN, 50f, 1f);
//
//            case "StoneThrower": // Rzucający kamieniami
//                return new Unit("Rzucający kamieniami", 25, 1, 8, 25, 50, 25, 12, x, y, 50, 50, Color.GRAY, 60f, 1.2f);
//
//            case "MammothRider": // Jeździec mamuta
//                return new Unit("Jeździec mamuta", 100, 2, 25, 90, 200, 100, 40, x, y, 80, 80, Color.DARK_GRAY, 150f, 2f);
//
//            case "BronzeSpearman": // Włócznik z brązu
//                return new Unit("Włócznik z brązu", 50, 1, 17, 51, 80, 50, 20, x, y, 50, 50, Color.ORANGE, 70f, 1.5f);
//
//            case "Archer": // Łucznik
//                return new Unit("Łucznik", 83, 1, 14, 43, 100, 83, 24, x, y, 50, 50, Color.GREEN, 100f, 1.5f);
//
//            case "Ballista": // Balista
//                return new Unit("Balista", 330, 2, 43, 153, 400, 330, 80, x, y, 100, 100, Color.PURPLE, 250f, 3f);
//
//            case "Knight": // Rycerz
//                return new Unit("Rycerz", 163, 1.5f, 29, 87, 160, 163, 40, x, y, 60, 60, Color.BLUE, 60f, 1.2f);
//
//            case "CompositeArcher": // Łucznik kompozytowy
//                return new Unit("Łucznik kompozytowy", 272, 1.5f, 23, 72, 200, 272, 48, x, y, 60, 60, Color.GREEN, 120f, 1.8f);
//
//            case "MountedKnight": // Rycerz konny
//                return new Unit("Rycerz konny", 1089, 2.5f, 72, 260, 800, 1089, 160, x, y, 80, 80, Color.GOLD, 90f, 1.8f);
//
//            case "Musketeer": // Muszkieter
//                return new Unit("Muszkieter", 539, 1.5f, 49, 147, 320, 539, 80, x, y, 60, 60, Color.LIGHT_GRAY, 150f, 2f);
//
//            case "Crossbowman": // Kusznik
//                return new Unit("Kusznik", 898, 1.5f, 39, 123, 400, 898, 96, x, y, 60, 60, Color.YELLOW, 150f, 1.8f);
//
//            case "Alchemist": // Alchemik
//                return new Unit("Alchemik", 3594, 2.5f, 123, 442, 1600, 3594, 320, x, y, 100, 100, Color.RED, 50f, 2.5f);
//
//            case "Arquebusier": // Arkebuzerzy
//                return new Unit("Arkebuzerzy", 1779, 2, 84, 251, 640, 1779, 160, x, y, 70, 70, Color.BLUE, 130f, 2f);
//
//            case "Rifleman": // Strzelcy
//                return new Unit("Strzelcy", 2965, 2, 67, 209, 800, 2965, 192, x, y, 70, 70, Color.TEAL, 160f, 2.2f);
//
//            case "GatlingGun": // Karabiny Gatlinga
//                return new Unit("Karabiny Gatlinga", 11859, 3, 209, 752, 3200, 11859, 640, x, y, 100, 100, Color.RED, 200f, 3.5f);
//
//            case "BayonetSoldier": // Żołnierz z bagnetem
//                return new Unit("Żołnierz z bagnetem", 5870, 2, 142, 426, 1280, 5870, 320, x, y, 80, 80, Color.LIME, 80f, 1.8f);
//
//            case "MP40Soldier": // Żołnierz z karabinem MP40
//                return new Unit("Żołnierz z karabinem MP40", 9784, 2, 114, 355, 1600, 9784, 384, x, y, 80, 80, Color.OLIVE, 80f, 1.6f);
//
//            case "WWITank": // Czołg z 1 wojny
//                return new Unit("Czołg z 1 wojny", 39135, 3, 355, 1278, 6400, 39135, 1280, x, y, 120, 120, Color.DARK_GRAY, 300f, 4f);
//
//            case "KnifeSoldier": // Żołnierz z nożem
//                return new Unit("Żołnierz z nożem", 19372, 2.5f, 241, 724, 2560, 19372, 640, x, y, 80, 80, Color.MAROON, 50f, 1.5f);
//
//            case "ModernRifleman": // Żołnierz z karabinem
//                return new Unit("Żołnierz z karabinem", 32287, 2.5f, 193, 603, 3200, 32287, 768, x, y, 90, 90, Color.SKY, 120f, 2f);
//
//            case "ModernTank": // Nowoczesny czołg
//                return new Unit("Nowoczesny czołg", 129147, 3.5f, 603, 2172, 12800, 129147, 2560, x, y, 150, 150, Color.ROYAL, 350f, 4.5f);
//
//            case "SpaceArmor": // Kosmiczny pancerz
//                return new Unit("Kosmiczny pancerz", 63928, 2.5f, 410, 1231, 5120, 63928, 1280, x, y, 100, 100, Color.DARK_GRAY, 200f, 3f);
//
//            case "Sniper": // Strzelec
//                return new Unit("Strzelec", 106546, 2.5f, 328, 1026, 6400, 106546, 1536, x, y, 90, 90, Color.MAGENTA, 200f, 3f);
//
//            case "Terminator": // Terminator
//                return new Unit("Terminator", 426184, 3.5f, 1026, 3693, 25600, 426184, 5120, x, y, 150, 150, Color.BLACK, 300f, 5f);
//
//            default:
//                throw new IllegalArgumentException("Nieznany typ jednostki: " + type);
//        }
//    }
//}
