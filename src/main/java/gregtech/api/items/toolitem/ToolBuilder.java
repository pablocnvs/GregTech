package gregtech.api.items.toolitem;

import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.block.Block;
import net.minecraft.util.SoundEvent;

import java.util.Collections;
import java.util.Set;
import java.util.function.UnaryOperator;

public abstract class ToolBuilder<T extends IGTTool> {

    protected final String domain, id;

    protected final Set<String> toolClasses = new ObjectArraySet<>();
    protected final Set<String> oreDicts = new ObjectArraySet<>();
    protected final Set<Block> effectiveBlocks = new ObjectOpenHashSet<>();

    protected int tier = -1;
    protected IGTToolDefinition toolStats;
    protected SoundEvent sound;
    protected AoEData aoeData = AoEData.of();

    public ToolBuilder(String domain, String id) {
        this.domain = domain;
        this.id = id;
    }

    public ToolBuilder<T> electric(int tier) {
        this.tier = tier;
        return this;
    }

    public ToolBuilder<T> toolStats(IGTToolDefinition toolStats) {
        this.toolStats = toolStats;
        return this;
    }

    public ToolBuilder<T> toolStats(UnaryOperator<TooDefinitionBuilder> builder) {
        this.toolStats = builder.apply(new TooDefinitionBuilder()).build();
        return this;
    }

    public ToolBuilder<T> sound(SoundEvent sound) {
        this.sound = sound;
        return this;
    }

    public ToolBuilder<T> toolClasses(String... tools) {
        Collections.addAll(toolClasses, tools);
        return this;
    }

    public ToolBuilder<T> oreDicts(String... ores) {
        Collections.addAll(oreDicts, ores);
        return this;
    }

    public ToolBuilder<T> effectiveBlocks(Block... blocks) {
        Collections.addAll(effectiveBlocks, blocks);
        return this;
    }

    public ToolBuilder<T> aoeData(int height, int width, int depth) {
        this.aoeData = AoEData.of(height, width, depth);
        return this;
    }

    public abstract T build();

}
