# Chat UI — Agent Guidance

Backend-agnostic chat UI components built with Helix + React.

## Quick commands

```bash
# Build library (ESM exports)
pnpm build

# Build demo app
pnpm build:app

# Dev server
pnpm dev

# Tests
pnpm test

# Lint
pnpm lint:kondo

# Clean
pnpm clean
```

## Architecture

- `:lib` build exports 8 ESM symbols: ChatPanel, MessageBubble, ChatComposer, useChatSession, createSolSession, createKnoxxSession, createOpencodeSession, createMockSession
- `IChatSession` protocol decouples transport from UI
- `marked` pinned to v4 (Closure compiler incompatibility with v12+)

## Dependencies

- Maven: helix 0.2.2, malli 0.17.0
- npm: react 18.3.1, react-dom 18.3.1, marked 4.3.0, dompurify
- No workspace or sibling dependencies

## License

GPL-3.0-or-later
