package pl.eventy;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.RegisteredServiceProvider;

/** Osobna klasa, zeby plugin dzialal tez bez Vault (klasa jest ladowana tylko gdy Vault istnieje). */
public class EconomyHook {

    private final Economy economy;

    private EconomyHook(Economy economy) {
        this.economy = economy;
    }

    public static EconomyHook create(EventsPlugin plugin) {
        RegisteredServiceProvider<Economy> rsp =
                plugin.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return null;
        }
        return new EconomyHook(rsp.getProvider());
    }

    public boolean deposit(OfflinePlayer player, double amount) {
        return economy.depositPlayer(player, amount).transactionSuccess();
    }
}
